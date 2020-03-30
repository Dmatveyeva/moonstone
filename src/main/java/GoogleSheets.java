import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GoogleSheets {
    private static Sheets sheetsService;
    private static String APPLICATION_NAME = "Google Sheets Example";
    private static String SPREADSHEET_ID = "158_7RAaMngT36dd_IuHnfMt5Il1Gvbv8wx87vx6fHes";
    ListGoogleSheets list = new ListGoogleSheets();


    private static Credential authorize() throws IOException, GeneralSecurityException {
        InputStream in = GoogleSheets.class.getResourceAsStream("/credentials.json");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                JacksonFactory.getDefaultInstance(), new InputStreamReader(in)
        );
        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);


        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(),
                clientSecrets, scopes).setDataStoreFactory(new FileDataStoreFactory(
                new java.io.File("tokens"))).setAccessType("offline").build();
        Credential credential = new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver()).authorize("user");
        return credential;
    }

    public static Sheets getSheetsService() throws IOException, GeneralSecurityException {
        Credential credential = authorize();
        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(), credential).setApplicationName(APPLICATION_NAME)
                .build();
    }

    //возврат списка таблицы
    public List<List<Object>> getGoogleSheets(String tables, String memberID) throws IOException, GeneralSecurityException {
        sheetsService = getSheetsService();
        ValueRange response = sheetsService.spreadsheets().values().get(SPREADSHEET_ID, tables).execute();

        List<List<Object>> values = response.getValues();
        if (values != null) {
            return list.getInfo(values, memberID);
        }
        return null;
    }

    public List<List<Object>> getGoogleSheets(String tables) throws IOException, GeneralSecurityException {
        sheetsService = getSheetsService();
        ValueRange response = sheetsService.spreadsheets().values().get(SPREADSHEET_ID, tables).execute();
        List<List<Object>> values = response.getValues();
        return values;
    }


    //изменение элемента таблицы
    public void updateGoogleSheets(String tablID, String element) throws IOException, GeneralSecurityException {
        sheetsService = getSheetsService();
        ValueRange body = new ValueRange().setValues(Arrays.asList(
                Arrays.asList(element)
        ));
        UpdateValuesResponse result = sheetsService.spreadsheets().values()
                .update(SPREADSHEET_ID, tablID, body).setValueInputOption("RAW")
                .execute();
    }

    //добавление
    public void addGoogleSheets(String tablID, List elem) throws IOException, GeneralSecurityException {
        sheetsService = getSheetsService();

        ValueRange appendBady = new ValueRange().setValues(Arrays.asList(
                elem
        ));

        AppendValuesResponse appendResult = sheetsService.spreadsheets().values()
                .append(SPREADSHEET_ID, tablID, appendBady)
                .setValueInputOption("USER_ENTERED")
                .setInsertDataOption("INSERT_ROWS")
                .setIncludeValuesInResponse(true)
                .execute();
    }

    public void newSpreadSheetID(String newName) {
        SPREADSHEET_ID = newName;
    }
}
