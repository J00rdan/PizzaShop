package pizzashop.repository;

import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.io.*;
import java.util.*;
import java.util.spi.ResourceBundleProvider;

public class PaymentRepository {
    private static String filename;
    private List<Payment> paymentList;

    public PaymentRepository(){
        Properties prop = new Properties();

        try {
            prop.load(new FileInputStream("/Users/andrei/Documents/Facultate/Anul 3/Semestrul 2/VVSS/Pizza/src/main/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        filename = prop.getProperty("payments_file");

        this.paymentList = new ArrayList<>();
        readPayments();
    }

    private void readPayments(){
        //ClassLoader classLoader = PaymentRepository.class.getClassLoader();
        File file = new File(filename);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while((line=br.readLine())!=null){
                Payment payment=getPayment(line);
                paymentList.add(payment);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Payment getPayment(String line){
        Payment item=null;
        if (line==null|| line.equals("")) return null;
        StringTokenizer st=new StringTokenizer(line, ",");
        int tableNumber= Integer.parseInt(st.nextToken());
        String type= st.nextToken();
        double amount = Double.parseDouble(st.nextToken());
        item = new Payment(tableNumber, PaymentType.valueOf(type), amount);
        return item;
    }

    public void add(Payment payment){
        paymentList.add(payment);
        writeAll();
    }

    public List<Payment> getAll(){
        return paymentList;
    }

    public void writeAll(){
        //ClassLoader classLoader = PaymentRepository.class.getClassLoader();
        File file = new File(filename);

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            for (Payment p:paymentList) {
                System.out.println(p.toString());
                bw.write(p.toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
