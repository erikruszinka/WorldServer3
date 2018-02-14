package sk.akademiasovy.world.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySQL {
    private Connection conn;
    private String driver = "com.mysql.jdbc.Driver";
    private String url="jdbc:mysql://localhost:3308/world_x";
    private String username="root";
    private String password="";
    public List<String> getCountries(){
        List<String> list = new ArrayList<>();
        try {
            Class.forName(driver).newInstance();
            conn= DriverManager.getConnection(url,username,password);
            String query = "SELECT Name from country";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                String name = rs.getString("name");
                list.add(name);
            }
        }catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
        System.out.println(list);
                return list;
    }
    public List<String> getCities(String country){
        List<String> list = new ArrayList<>();
        try {
            Class.forName(driver).newInstance();
            conn= DriverManager.getConnection(url,username,password);
            String query = "SELECT city.name from city INNER JOIN country on city.countrycode"+
                    "= country.code where country.name like '"+country+"'";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                String name = rs.getString("name");
                list.add(name);
            }
        }catch(Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
        System.out.println(list);
        return list;
    }
}
