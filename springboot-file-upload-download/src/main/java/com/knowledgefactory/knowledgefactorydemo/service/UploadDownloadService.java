package com.knowledgefactory.knowledgefactorydemo.service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadDownloadService {
    private static final String path = "C:\\Users\\hadday\\Desktop\\mmm";


    public List<String> uploadFile(MultipartFile file) throws Exception {

        // Save file on system
        if (!file.getOriginalFilename().isEmpty()) {
			/*Stream<String> lines;
			BufferedOutputStream outputStream = new BufferedOutputStream(
					new FileOutputStream(new File(path, file.getOriginalFilename())));
			outputStream.write(file.getBytes());
			outputStream.flush();
			outputStream.close();*/

            try {
                // Create a BufferedReader to read the file
                BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));


                // Read and process the file contents
                String line;
                StringBuilder modifiedContent = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    // Modify the line of text
                    if (line.contains("  OTHR")) {
                        line = line.replace("  OTHR", "61OTHR");
                    }
                    modifiedContent.append(line).append("\n");
                    System.out.println(line);

                }

                // Close the BufferedReader
                reader.close();


                // Write the modified contents back to the file
                File outputFile = new File(path, file.getOriginalFilename());
                FileOutputStream outputStream = new FileOutputStream(outputFile);
                outputStream.write(modifiedContent.toString().getBytes());
                outputStream.close();
				/*File outputFile = new File(path,file.getOriginalFilename());
				FileOutputStream outputStream = new FileOutputStream(outputFile);
				outputStream.write(file.getBytes());
				outputStream.close();*/

            } catch (IOException e) {
                e.printStackTrace();
            }


        } else {
            throw new Exception();
        }

        List<String> list = new ArrayList<String>();
        File files = new File(path);
        String[] fileList = ((File) files).list();
        for (String name : fileList) {
            list.add(name);
        }

        return list;

    }


    public List<String> getListofFiles() throws Exception {

        List<String> list = new ArrayList<String>();
        File files = new File(path);
        String[] fileList = files.list();
        for (String name : fileList) {
            list.add(name);
        }

        return list;

    }
}
