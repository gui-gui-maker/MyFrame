package com.fusioncharts.exporter.resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.batik.transcoder.TranscoderException;

import com.fusioncharts.exporter.ErrorHandler.LOGMESSAGE;
import com.fusioncharts.exporter.ErrorHandler.Status;
import com.fusioncharts.exporter.FusionChartsExportHelper;
import com.fusioncharts.exporter.SvgUtil;
import com.fusioncharts.exporter.beans.ChartMetadata;
import com.fusioncharts.exporter.beans.ExportBean;
import com.fusioncharts.exporter.beans.ExportConfiguration;
import com.fusioncharts.exporter.beans.LogMessageSetVO;
import com.fusioncharts.exporter.generators.PDFGenerator;
import com.khnt.utils.FileUtil;

public class FCExporter_PDF extends FCExporter_Format
{
  private ExportBean exportBean = null;

  public String exportOutput(Object exportObj, HttpServletResponse response)
  {
    byte[] pdfBytes = (byte[])exportObj;
    String action = (String)this.exportBean
      .getExportParameterValue("exportaction");
    String exportFormat = (String)this.exportBean
      .getExportParameterValue("exportformat");
    String exportTargetWindow = (String)this.exportBean
      .getExportParameterValue("exporttargetwindow");

    String fileNameWithoutExt = (String)this.exportBean
      .getExportParameterValue("exportfilename");
    String extension = 
      FusionChartsExportHelper.getExtensionFor(exportFormat.toLowerCase());

    String fileName = fileNameWithoutExt + "." + extension;

    String stream = this.exportBean.getStream();
    ChartMetadata metadata = this.exportBean.getMetadata();

    boolean isHTML = false;
    if (action.equals("download")) {
      isHTML = true;
    }
    LogMessageSetVO logMessageSetVO = new LogMessageSetVO();

    String noticeMessage = "";
    String meta_values = this.exportBean.getMetadataAsQueryString(null, false, 
      isHTML);

    if (!action.equalsIgnoreCase("download")) {
      noticeMessage = "&notice=";
      String pathToWebAppRoot = (String)this.exportBean
        .getExportParameterValue("webapproot");

      String pathToSaveFolder = ExportConfiguration.SAVEABSOLUTEPATH;
      File saveFolder = new File(pathToSaveFolder);

      String completeFilePath = pathToSaveFolder + File.separator + 
        fileName;
      String completeFilePathWithoutExt = pathToSaveFolder + 
        File.separator + fileNameWithoutExt;
      File saveFile = new File(completeFilePath);
      if (saveFile.exists()) {
        noticeMessage = noticeMessage + LOGMESSAGE.W509;
        if ((!ExportConfiguration.OVERWRITEFILE) && 
          (ExportConfiguration.INTELLIGENTFILENAMING)) {
          noticeMessage = noticeMessage + LOGMESSAGE.W514;
          completeFilePath = 
            FusionChartsExportHelper.getUniqueFileName(completeFilePathWithoutExt, 
            extension);
          File tempFile = new File(completeFilePath);
          fileName = tempFile.getName();
          noticeMessage = noticeMessage + LOGMESSAGE.W515 + fileName;
          logMessageSetVO.addWarning(LOGMESSAGE.W515);
        }
      }

      try
      {
        FileOutputStream fos = new FileOutputStream(completeFilePath);

        for (int i = 0; i < pdfBytes.length; i++)
          fos.write(pdfBytes[i]);
        fos.flush();
        fos.close();
      }
      catch (FileNotFoundException e1) {
        e1.printStackTrace();
      } catch (IOException e) {
        logMessageSetVO.addError(LOGMESSAGE.E600);
      }

      String pathToDisplay = ExportConfiguration.HTTP_URI + "/" + 
        fileName;
      if (ExportConfiguration.HTTP_URI.endsWith("/")) {
        pathToDisplay = ExportConfiguration.HTTP_URI + fileName;
      }

      meta_values = this.exportBean.getMetadataAsQueryString(pathToDisplay, 
        false, isHTML);

      if ((logMessageSetVO.getErrorsSet() == null) || 
        (logMessageSetVO.getErrorsSet().isEmpty()))
      {
        try
        {
          PrintWriter out = response.getWriter();
          out.print(meta_values + noticeMessage + "&statusCode=" + 
            Status.SUCCESS.getCode() + "&statusMessage=" + 
            Status.SUCCESS);
        }
        catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    else
    {
      response.setContentType(
        FusionChartsExportHelper.getMimeTypeFor(exportFormat.toLowerCase()));

      if (exportTargetWindow.equalsIgnoreCase("_self")) {
        response.addHeader("Content-Disposition", 
          "attachment; filename=\"" + fileName + "\"");
      }
      else {
        response.addHeader("Content-Disposition", "inline; filename=\"" + 
          fileName + "\"");
      }
      try
      {
        OutputStream os = response.getOutputStream();

        for (int i = 0; i < pdfBytes.length; i++)
          os.write(pdfBytes[i]);
        os.flush();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    if ((logMessageSetVO.getErrorsSet() == null) || 
      (logMessageSetVO.getErrorsSet().isEmpty())) {
      meta_values = this.exportBean.getMetadataAsQueryString(null, true, 
        isHTML);
      try
      {
        PrintWriter out = response.getWriter();
        out.print(meta_values + noticeMessage + "&statusCode=" + 
          Status.SUCCESS.getCode() + "&statusMessage=" + 
          Status.SUCCESS);
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  public Object exportProcessor(ExportBean pExportBean)
  {
    this.exportBean = pExportBean;
    String stream = this.exportBean.getStream();
    byte[] pdfBytes = null;
    if(stream.indexOf("svg")!=-1){
    	try {
			String fileNameWithoutExt = (String) this.exportBean
					.getExportParameterValue("exportfilename");
			String fileName = fileNameWithoutExt + ".pdf";
			String pathToSaveFolder = ExportConfiguration.SAVEABSOLUTEPATH;
			String completeFilePath = pathToSaveFolder + File.separator+ fileName;
			pdfBytes = SvgUtil.convertStr2PdfToByte(stream, new File(completeFilePath));
			FileUtil.delAllFile(completeFilePath);
		} catch (IOException e) {
			return null;
		} catch (TranscoderException e) {
			return null;
		}
    }else{
    	  ChartMetadata metadata = this.exportBean.getMetadata();
    	  PDFGenerator pdf = new PDFGenerator(stream, metadata);
    	  pdfBytes = pdf.getPDFObjects(true);
    }
    return pdfBytes;
  }
}