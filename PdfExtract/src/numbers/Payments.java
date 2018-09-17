package numbers;

import java.text.DecimalFormat;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class Payments {
	
	public static String value = ".8.180.18";

	public static void main(String[] args) {
		String result = formatPayValue(value.replaceAll("[,.]", ""));
		System.out.println(result);
	}

	private static String formatPayValue(String value){

        try {
        	String cents = "," + value.substring(value.length()-2);
            String coin = value.substring(0, value.length()-2);
            
            double payValue = Double.parseDouble(coin);
            DecimalFormat formatter = new DecimalFormat("###,###");

            coin = String.valueOf(formatter.format(payValue));
            String result = coin + cents;

            return result;
        }catch (Exception e){
            String error = "Error formatting field 'VALOR_TOTAL_FATURA': " + ExceptionUtils.getStackTrace(e);
            return error;
        }

    }
	
}
