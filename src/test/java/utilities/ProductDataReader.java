package utilities;

import java.util.List;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductDataReader 
{
	
	public static <T> Object[][] readJSONDataProvider(String filepath,TypeReference<List<T>> typeref) throws IOException
	{
		ObjectMapper mapper=new ObjectMapper();
		try {
			List<T> data=mapper.readValue(new File(filepath), typeref);
			
			Object result[][]=new Object[data.size()][1];
			
			for(int i=0;i<data.size();i++)
			{
				result[i][0]=data.get(i);
			}
			return result;
	
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Object[0][0];
		}
		
	}

}
