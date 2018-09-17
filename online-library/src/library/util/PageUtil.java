package library.util;

import library.dao.BookDao;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PageUtil {

//    public int countPage(){
//        return (BookDao.getInstance().countBook()/10)+1;
//    }

    public static int countPageString(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader(fileName));
        String line = null;
        int count=0;
        while( ( line = reader.readLine() ) != null ) {
            count++;

        }
        return (count/50)+1;
    }

}
