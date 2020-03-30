import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class ListGoogleSheets {


    public List<List<Object>> getInfo(List<List<Object>> list, String memberID) throws IOException, GeneralSecurityException {
        if (list == null || list.isEmpty()) {
            System.out.println("No data found");
        } else {
            int i = 0;
            for (List row : list) {
                i++;
                if (row.get(0).equals(memberID)) {
                    row.add(i);
                    return row;
                }
            }
        }
        return null;
    }



}