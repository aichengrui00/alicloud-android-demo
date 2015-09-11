package alibaba.mas_restful_demo;

import java.util.List;

/**
 * Created by ryan on 25/8/15.
 */
public class MASRestfulRawEditionUpload {

    MASRestfulRawEditionUpload() {
        System.out.println("MASRESTful raw init.");
    }

    public static void main(String appKeyTemp, String appSecret) {
        String appKey = String.valueOf(appKeyTemp);
        String charset = "UTF-8";
        // RESTful service.
        String requestURL = "http://adash.mas.aliyuncs.com:80/rest/restful";

        try {
            String type = "Raw";
            String content = "Please input the content you want to post";

            byte[] lsignData = MultipartUtility.getSign(appKey, appSecret, type, content);
            String queryString = "?ak=" + appKey + "&s=" + MultipartUtility.toHex(lsignData);
            MultipartUtility multipart = new MultipartUtility(requestURL + queryString, charset);
            multipart.addHeaderField("Test-Header", "Header-Value");
            multipart.addFormField(type, content);

            List<String> response = multipart.finish();
            System.out.println("SERVER REPLIED:");
            for (String line : response) {
                System.out.println(line);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
