package utilities;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;

public class DataProviderClass 

{
	@DataProvider
	public Object[][] getProductTestData() throws IOException
	{
		return ProductDataReader.readJSONDataProvider("./testData/loginData.JSON", new TypeReference<List<ProductData>>() {});
	}

}
