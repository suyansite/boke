package com.databaker.hyzx.utils;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.*;
import java.math.BigDecimal;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;



public class AudioUtil {

    private static Logger logger = LoggerFactory.getLogger(AudioUtil.class);



    /**
     * 获取语音文件播放时长(秒) 支持wav 格式
     * @param filePath
     * @return
     */
    public static int getDuration(String filePath){
        try{

            File destFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(destFile);
            AudioFormat format = audioInputStream.getFormat();
            long audioFileLength = destFile.length();
            int frameSize = format.getFrameSize();
            Float frameRate = format.getFrameRate();
            BigDecimal b = new BigDecimal(String.valueOf(frameRate));
            double d = b.doubleValue();
            int durationInSeconds = (int)(audioFileLength / (frameSize * d));
            return durationInSeconds;

        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }


    /**
     * 转换文件大小
     */
    public static String formatFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    public static List<String> splitFile(File file,Integer size) throws IOException {//文件切割
        FileInputStream fis = new FileInputStream(file);//创建读取流对象与被切割文件关联
        FileOutputStream fos = null;
        byte[] buf = new byte[1024 * 1024 * size ];
        int len = 0;
        int count = 1;
        List<String> list = new ArrayList<String>();
        while ((len = fis.read(buf)) != -1) {
            int t = count++;
            String path = file.getParentFile()+"/"+file.getName().substring(0,file.getName().lastIndexOf("."))+"_"+(t)+".mp3";
            String newPath = file.getParentFile()+"/"+file.getName().substring(0,file.getName().lastIndexOf("."))+"/"+file.getName().substring(0,file.getName().lastIndexOf("."))+"_"+(t)+".mp3";
            createDir(file.getParentFile()+"/"+file.getName().substring(0,file.getName().lastIndexOf("."))+"/");
            File cloudFile = new File(newPath);
            if(!cloudFile.exists())
            {
                try {
                    cloudFile.createNewFile();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //每读取1024KB源文件，则用输出流存储1024KB文件到指定目录下，然后输出流
            fos = new FileOutputStream(path);
            fos.write(buf, 0, len);
            fos.close();
            convertPcm(path, newPath);
            list.add(newPath);
            new File(path).delete();
        }
        return list;
    }


    public static List<String> splitPcmFile(File file,Integer size) throws IOException {//文件切割
        FileInputStream fis = new FileInputStream(file);//创建读取流对象与被切割文件关联
        FileOutputStream fos = null;
        byte[] buf = new byte[1024 * 1024 * size ];
        int len = 0;
        int count = 1;
        List<String> list = new ArrayList<String>();
        while ((len = fis.read(buf)) != -1) {
            int t = count++;
            //String path = file.getParentFile()+"/"+file.getName().substring(0,file.getName().lastIndexOf("."))+"_"+(t)+".pcm";
            String path = file.getParentFile()+"/"+file.getName().substring(0,file.getName().lastIndexOf("."))+"/"+file.getName().substring(0,file.getName().lastIndexOf("."))+"_"+(t)+".pcm";
            String newPath = file.getParentFile()+"/"+file.getName().substring(0,file.getName().lastIndexOf("."))+"/"+file.getName().substring(0,file.getName().lastIndexOf("."))+"_"+(t)+".mp3";
            createDir(file.getParentFile()+"/"+file.getName().substring(0,file.getName().lastIndexOf("."))+"/");
            //每读取1024KB源文件，则用输出流存储1024KB文件到指定目录下，然后输出流
            fos = new FileOutputStream(path);
            fos.write(buf, 0, len);
            fos.close();
            convertPcm(path, newPath);
            list.add(path);
        }
        return list;
     }



    /**
     * @param src    待转换文件路径
     * @param target 目标文件路径(这个工具是在源文件的基础上再形成一个文件，target样例：D:/landscape/music/25745664665456.mp3,必须写全否则报io‘无法访问错误’)
     * @throws IOException 抛出异常
     */
   public static String convertPcm(String src, String target) throws IOException {
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(target);

        //计算长度
        byte[] buf = new byte[1024 * 4];
        int size = fis.read(buf);
        int PCMSize = 0;
        while (size != -1) {
            PCMSize += size;
            size = fis.read(buf);
        }
        fis.close();

        //填入参数，比特率等等。这里用的是16位单声道 16000 hz
        WaveHeader header = new WaveHeader();
        //长度字段 = 内容的大小（PCMSize) + 头部字段的大小(不包括前面4字节的标识符RIFF以及fileLength本身的4字节)
        header.fileLength = PCMSize + (44 - 8);
        header.FmtHdrLeth = 16;
        header.BitsPerSample = 16;
        header.Channels = 1;
        header.FormatTag = 0x0001;
        header.SamplesPerSec = 16000;
        header.BlockAlign = (short) (header.Channels * header.BitsPerSample / 8);
        header.AvgBytesPerSec = header.BlockAlign * header.SamplesPerSec;
        header.DataHdrLeth = PCMSize;

        byte[] h = header.getHeader();

        assert h.length == 44; //WAV标准，头部应该是44字节
        //write header
        fos.write(h, 0, h.length);
        //write data stream
        fis = new FileInputStream(src);
        size = fis.read(buf);
        while (size != -1) {
            fos.write(buf, 0, size);
            size = fis.read(buf);
        }
        fis.close();
        fos.close();
        logger.info("Convert to OK!");
        return "ok";
    }


    /**
     * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(String filename) throws IOException {

        FileChannel fc = null;
        try {
            fc = new RandomAccessFile(filename, "r").getChannel();
            MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0,
                    fc.size()).load();
            //System.out.println(byteBuffer.isLoaded());
            byte[] result = new byte[(int) fc.size()];
            if (byteBuffer.remaining() > 0) {
                // System.out.println("remain");
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                fc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 创建文件夹（目录），存在不可覆盖
     * @param destDirName 创建路径（D:/com/filePath）
     * @return
     */
    public static boolean createDir(String destDirName) {

        File dir = new File(destDirName);
        if(dir.exists()) {
            logger.info("创建目录{}失败，目标目录已存在！",destDirName);
            //System.out.println("创建目录" + destDirName + "失败，目标目录已存在！");
            return false;
        }
        if(!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        // 创建单个目录
        if(dir.mkdirs()) {
            //System.out.println("创建目录" + destDirName + "成功！");
            logger.info("创建目录{}成功",destDirName);
            return true;
        }else{
            logger.info("创建目录{}成功",destDirName);
            // System.out.println("创建目录" + destDirName + "成功！");
            return false;
        }
   }

   public static Map<String,File> creatFile(LinkedBlockingQueue<byte[]> fileQueue, String rootPath) {
        if (fileQueue.size()>0) {
            logger.info("会议结束，执行录音文件存储");
            FileOutputStream fileOutputStream = null;
            LocalDateTime ldt = LocalDateTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

            DateTimeFormatter sysDateFmt = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss_SSS");
            String fileName = sysDateFmt.format(ldt);

            //拼装路径，上存储的路径 2021/6/5/xxxx.jpg
            //2021/6/5/
            String folder = dtf.format(ldt);
            //新文件名
            String localPath = rootPath + folder + "/" + fileName + ".pcm";
            createDir( rootPath + folder);
            File cloudFile = new File(localPath);
            try {
                if (!cloudFile.exists()) {
                    try {
                        cloudFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                fileOutputStream = new FileOutputStream(cloudFile);
                for (byte[] b : fileQueue) {
                    fileOutputStream.write(b, 0, b.length);
                }
                fileOutputStream.flush();

                String wavlocalPath = localPath.substring(0, localPath.length() - 3) + "wav";
                convertPcm(localPath, wavlocalPath);
                String publicPath =rootPath.substring(0,rootPath.lastIndexOf("mp3/"));
                String jsonPath = publicPath+"json" +"/"+ folder + "/" + fileName + ".json";
                createDir( publicPath+"json" +"/"+ folder );
                String sh = audioWaveToJson(wavlocalPath,jsonPath);
                Runtime.getRuntime().exec(sh);//创建音频波形文件
                Map<String,File> result = new HashMap<String,File>();
                result.put("audioFile",new File(wavlocalPath));
                result.put("jsonFile",new File(jsonPath));
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

   public static String audioWaveToJson(String filePath, String audioWaveJsonPath) {
        List<String> cmd = new ArrayList<>();
        cmd.add("audiowaveform");
        cmd.add("-i");
        cmd.add(filePath);
        cmd.add("-o");
        cmd.add(audioWaveJsonPath);
        cmd.add("-z 256 -b 16 --split-channels");
        return cmdBuild(cmd);
   }

   public static String cmdBuild(List<String> ffmpegCmds) {
        StringBuilder builder = new StringBuilder();
        for (String cmd : ffmpegCmds) {
            builder.append(cmd.trim()).append(" ");
        }
        return builder.toString();
   }



    public static double calcDecibelLevel(short[] buffer, int readSize) {

        long v = 0;
        // 将 buffer 内容取出，进行平方和运算
        for (int i = 0; i < buffer.length; i++) {
            v += buffer[i] * buffer[i];
        }
        // 平方和除以数据总长度，得到音量大小。
        double mean = v / (double) readSize;
        double volume = 8 * Math.log10(mean);
        return volume;

    }


    public static short[] byteArray2ShortArray(byte[] data, int items) {
        short[] retVal =new short[items];
        for (int i =0; i < retVal.length; i++){
            retVal[i] = (short) ((data[i *2]&0xff) | (data[i *2+1]&0xff) <<8);
           // logger.info("short[i]={}",retVal[i] );
        }


        return retVal;
    }

}




