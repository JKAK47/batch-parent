package org.vincent.cache;

/**
 * batch-parent.org.vincent.cache <br/>
 * Created by PengRong on 2018/6/29. <br/>
 *
 * @author PengRong <br/>
 * @Description TODO(${END})
 * @ClassName: ${CLASS}
 * @since 2018-06-29 9:32 <br/>
 */
public class App
{
    public static void main(String[] args){
        //获取当前用户目录，userHome:C:\Users\liuhy
        String userHome = System.getProperty("user.home");
        //user.dir是用户当前的工作目录，在tomcat中对应的目录是bin目录，所以user.dir指向tomcat/bin，
        //java工程返回的工程根目录，userDir:D:\Dev\WorkStation\IdeaProjects\batch-parent
        String userDir = System.getProperty("user.dir");
        //java.io.tmpdir是默认的临时目录，tomcat的临时目录是temp目录，所以这里java.io.tmpdir指向tomcat/temp
        //这里返回的 tmpDir:C:\Users\liuhy\AppData\Local\Temp\
        String tmpDir = System.getProperty("java.io.tmpdir");

        System.out.println("userHome:"+userHome);
        System.out.println("userDir:"+userDir);
        System.out.println("tmpDir:"+tmpDir);
    }
}
