package common;

import java.io.File;

/**
 * batch-parent.common <br/>
 * Created by PengRong on 2018/6/10. <br/>
 *
 * @author PengRong <br/>
 * @Description 关于文件流获取和删除操作工具类(${END})
 * @ClassName: ${CLASS}
 * @since 2018-06-10 1:30 <br/>
 */

public class FileUtils {


    /**
     * @param cloudpath 需要删除的目录
     * @return 返回文件删除成功与否
     * @throws Exception
     */
    public boolean deleteFilePath(String cloudpath)throws Exception{
        boolean flag=false;

        //检查目录路径
        File dir=new File(cloudpath);
        if(!dir.exists()){
            flag=true;
            return flag;
        }
        flag=deleteFile(dir);


        return flag;
    }
    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param filePath 将要删除的文件目录
     */
    private static boolean deleteFile(File filePath) {
        if (filePath.isDirectory()) {
            //获取该文件夹下所有的文件的文件名和文件夹名称
            String[] childrenFile = filePath.list();
            //递归删除目录中的子目录下所有文件以及子目录和他的子文件
            for (int i=0; i<childrenFile.length; i++) {
                boolean success = deleteFile(new File(filePath, childrenFile[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return filePath.delete();
    }


    public static void main(String[] args) throws Exception {
        FileUtils fileUtils=new FileUtils();
        System.out.println(fileUtils.deleteFilePath("C:\\Users\\liuhy\\Desktop\\124"));
        System.out.println( System.getProperty("java.io.tmpdir"));

    }
}
