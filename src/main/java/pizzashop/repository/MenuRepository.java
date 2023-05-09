package pizzashop.repository;

import pizzashop.model.MenuDataModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

public class MenuRepository {
    private static String filename;
    private List<MenuDataModel> listMenu;

    public MenuRepository(){
        Properties prop = new Properties();

        try {
            prop.load(new FileInputStream("C:\\Users\\drago\\OneDrive\\Desktop\\VVSS\\lab3\\PizzaShop\\src\\main\\resources\\config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        filename = prop.getProperty("menu_file");
    }

    private void readMenu(){
        //ClassLoader classLoader = MenuRepository.class.getClassLoader();
        File file = new File(filename);
        this.listMenu= new ArrayList();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while((line=br.readLine())!=null){
                MenuDataModel menuItem=getMenuItem(line);
                listMenu.add(menuItem);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MenuDataModel getMenuItem(String line){
        MenuDataModel item=null;
        if (line==null|| line.equals("")) return null;
        StringTokenizer st=new StringTokenizer(line, ",");
        String name= st.nextToken();
        double price = Double.parseDouble(st.nextToken());
        item = new MenuDataModel(name, 0, price);
        return item;
    }

    public List<MenuDataModel> getMenu(){
        readMenu();//create a new menu for each table, on request
        return listMenu;
    }
}