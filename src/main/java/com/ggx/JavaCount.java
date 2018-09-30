package com.ggx;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

/**
 * Created by mi on 2018/9/27.
 */
@Mojo(name="java-count", defaultPhase = LifecyclePhase.COMPILE)
public class JavaCount extends AbstractMojo{

    @Parameter
    private String path;

    public void execute() throws MojoExecutionException, MojoFailureException {
        File file = new File(path);
        count(file);
        System.out.println("=====" + count);
    }

    private int count = 0;

    private void count(File file){
        if(file.isFile() && file.getName().contains(".java")){

            count++;
        }else{
            File[] files = file.listFiles();
            if(files == null) return ;
            for(File f : files){
                if(f.isFile() && f.getName().contains(".java")){
                    count++;
                }else{
                    count(f);
                }
            }
        }
    }

    public static void main(String[] args) {
        JavaCount javaCount = new JavaCount();
        javaCount.path = "C:\\mySoftware\\workspace\\maven-java-count";
        javaCount.count(new File(javaCount.path));
    }
}
