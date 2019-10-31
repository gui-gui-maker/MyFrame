package com.fusioncharts.exporter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SvgPngConverter {
	public static void convertToPngByFile(String filePath, String pngFilePath,Map<String, String> map)
            throws IOException, TranscoderException {
        File file = new File(pngFilePath);
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            convertToPngByFile(filePath, outputStream,map);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
     
    public static void convertToPngByFile(String path, OutputStream outputStream,Map<String, String> map)
            throws TranscoderException, IOException {
        try {
            File file = new File(path);
            String parser = XMLResourceDescriptor.getXMLParserClassName();
            SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
            Document doc = f.createDocument(file.toURI().toString());
            for (int i = 1; i <=map.size()/3; i++) {
                Element e = doc.getElementById(map.get("id"+i));
                System.out.println(map.get("name"+i));
                e.setAttribute(map.get("name"+i), map.get("value"+i));
            }
            PNGTranscoder t = new PNGTranscoder();
            TranscoderInput input = new TranscoderInput(doc);
            TranscoderOutput output = new TranscoderOutput(outputStream);
            t.transcode(input, output);
            outputStream.flush();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
    	Map<String, String> map = new HashMap<String, String>();
    	try {
			convertToPngByFile("E:\\tt.svg","E:\\tt.jpg",map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TranscoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
