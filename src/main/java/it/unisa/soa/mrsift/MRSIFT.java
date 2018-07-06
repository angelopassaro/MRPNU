package it.unisa.soa.mrsift;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 *
 * @author didacus
 */
public class MRSIFT {

  private static final String OPENCV_LIB = "/libopencv_java341.so";

  public static void load_library() {
    URL url = MRSIFT.class.getResource(OPENCV_LIB);
    File opencv = new File(url.getFile());
    System.load(opencv.getAbsolutePath());
    
  }

  public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
    load_library();
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "MRSIFT");
    job.setJarByClass(MRSIFT.class);
    job.setMapperClass(SiftMapper.class);
    job.setCombinerClass(SiftReduce.class);
    job.setInputFormatClass(ImageFileFormat.class);
    FileOutputFormat.setOutputPath(job, new Path(args[1]);
    FileOutputFormat.
    ImageFileFormat.addInputPath(job, new Path(args[0]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
