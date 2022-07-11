import java.net.*;
import java.io.*;
import java.nio.channels.*;
import java.util.Properties;
public class MvnWrpDow{
    private static final String WRP_VER="3.1.0";
    private static final String DEFAULT_DOWNLOAD_URL="https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/"+WRP_VER+"/maven-wrapper-"+WRP_VER+".jar";
    private static final String Mvn_Wrp_PROPERTIES_PATH=".mvn/wrapper/maven-wrapper.properties";
    private static final String Mvn_Wrp_JAR_PATH=".mvn/wrapper/maven-wrapper.jar";
    private static final String PROPERTY_NAME_WRAPPER_URL="wrapperUrl";
    public static void main(String[] args){
        System.out.println("- Using the BASE directory: "+baseDirectory.getAbsolutePath());
        File MvnWrpPropertyFile=new File(baseDirectory,Mvn_Wrp_PROPERTIES_PATH);
        String url=DEFAULT_DOWNLOAD_URL;
        if(MvnWrpPropertyFile.exists()){
            FileInputStream MvnWrpPropertyFileInputStream=null;
            try{
                MvnWrpPropertyFileInputStream=new FileInputStream(MvnWrpPropertyFile);
                Properties MWProperties=new Properties();
                MWProperties.load(MvnWrpPropertyFileInputStream);
                url=MWProperties.getProperty(PROPERTY_NAME_WRAPPER_URL,url);
                }catch(IOException e){
                    System.out.println("- ERROR Loading '"+Mvn_Wrp_PROPERTIES_PATH+"'");
                }finally{
                    try{
                        if(MvnWrpPropertyFileInputStream !=null){
                            MvnWrpPropertyFileInputStream.close();
                        }
                    }catch(IOException e) {//Ignore...}                  
                    
                }
        }
        System.out.println("- Downloading from: "+url);
        File outputfile=new File(baseDirectory.getAbsolutePath(),Mvn_Wrp_JAR_PATH);
        if(!outputfile.getParentFile().exists()){
            if(!outputfile.getParentFile().mkdirs()){
                System.out.println("- ERROR creating output directory '"+outputfile.getParentFile().getAbsolutePath()+"'");
            }
        }
        System.out.println("- Downloading to: "+outputfile.getAbsolutePath());
        try{
            downloadFileFromURL(url,outputfile);
            System.out.println("Done");
            System.exit(0);
            }catch(Throwable e){
                System.out.println("- Error downloading");
                e.printStackTrace();
                System.exit(1);
            }
    }
    private static void downloadFileFromURL(String urlstring, File destination)throws Exception{
        if(System.getenv("MVNW_USERNAME")!=null && System.getenv("MVNW_PASSWORD")!=null){
            String username=System.getenv("MVNW_USERNAME");
            char[] password=System.getenv("MVNW_PASSWORD").toCharArray();
            Authenticator.setDefault(new Authenticator(){
                @Override
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(username,password);
                }
            }); 
        }
        URL website=new URL(urlstring);
        ReadableByteChannel RBC;
        RBC=Channels.newChannel(website.openStream());
        FileOutputStream fos=new FileOutputStream(destination);
        fos.getChannel().transferFrom(RBC,0,long.MAX_VALUE);
        fos.close();
        RBC.close();
    }
}
} 
