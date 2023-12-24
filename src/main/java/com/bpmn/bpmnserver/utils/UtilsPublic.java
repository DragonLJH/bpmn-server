package com.bpmn.bpmnserver.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Component
public class UtilsPublic {

    private String BPMN_PATH_WIN = "D:\\HZ\\dragon\\bpmn";

    private String FORM_PATH_WIN = "D:\\HZ\\dragon\\form";

    public void createBpmnFile(MultipartFile filePart, String fileName, String path) throws Exception {
        path = BPMN_PATH_WIN + path;
        createFile(filePart, fileName, path);
    }

    public String readBpmnFile(String formId, String fileName) {
        String path = BPMN_PATH_WIN + "\\" + formId + "\\" + fileName;
        return readFile(path);
    }

    public void createFormFile(MultipartFile filePart, String fileName, String path) throws Exception {
        path = FORM_PATH_WIN + path;
        createFile(filePart, fileName, path);
    }

    public String readFormFile(String formId, String fileName) {
        String path = FORM_PATH_WIN + "\\" + formId + "\\" + fileName;
        return readFile(path);
    }


    private void createFile(MultipartFile filePart, String fileName, String path) throws Exception {
        File f = new File(path);
        if (f.exists()) {// 判断文件夹是否存在
        } else {
            // 先创建文件所在的目录
            f.mkdirs();
        }
        OutputStream out = null;
        InputStream fileContent = null;
        String pathname = path + File.separator + fileName; // 得到上传文件路径+文件名
        out = new FileOutputStream(new File(pathname)); // 创建输出流
        fileContent = filePart.getInputStream(); // 得到输入流，即文件内容
        final byte[] bytes = new byte[1024]; // 创建数组，准备每次读1024字节，即1k
        // 为了提高输出效率，每次从文件内容中读1024字节，然后输出。也可用BufferedOutputStream。
        // 直至read返回-1，表示读完了。
        int read = fileContent.read(bytes); // 读到的字节数
        while (read != -1) {
            out.write(bytes, 0, read); // 写，即输出
            read = fileContent.read(bytes); // 接着读
        }
        out.close();// 关闭输入输出流
        fileContent.close();
    }

    private String readFile(String path) {
        String res = "";
        try {
            //创建FileReader对象，指定要读取的文件
            FileReader fileReader = new FileReader(path);
            //定义一个int类型的变量length，并令其等于零
            int length = 0;
            //通过循环来判断是否读取到文件的末尾
            while ((length = fileReader.read()) != -1) {
                //输出读取到的字符
                res += (char) length;
            }
            //关闭流
            fileReader.close();
        } catch (Exception e) {
        }
        return res;
    }

    /*
     * 返回的新唯一文件名 = UUID + 原文件的后缀名。
     */
    public String generateUniqueFileName(String originalName) {
        if (originalName == null) {
            return null;
        }
        // 生成唯一的文件名如下，以“宋智孝.jpg”为例
        int suffixIndex = originalName.lastIndexOf("."); // 取得后缀的位置，如3
        String suffix = originalName.substring(suffixIndex); // 取得后缀，如.jpg
        String uuid = UUID.randomUUID().toString(); // 生成UUID，并转化为String
        uuid = uuid.replaceAll("-", ""); // 去掉UUID的-符号
        String uniqueName = uuid + suffix;
        return uniqueName;
    }

}
