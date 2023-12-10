package sample;

public class Test {
	
	public static void main(String[] args) {
		try {
            System.out.println("main fn : 1");
            try {
            	throw new NullPointerException();
            }	catch (NullPointerException ex) {
                throw new Exception(); 
            }
            
            //System.out.println("main fn : 2");
        }  catch (Exception ex) {
            ex.printStackTrace();
        }
	}

}
