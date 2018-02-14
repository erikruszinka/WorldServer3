package sk.akademiasovy.world.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import sk.akademiasovy.world.db.MySQL;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/world")
public class World{
    @GET
    @Path("/countries")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCountries(){
        MySQL mySQL=new MySQL();
        List<String> list= mySQL.getCountries();
        System.out.println(list);
        boolean b =false;
        String result="funk({\"name\":[";
        for(String temp:list){
            if(b==true) {
                result+= ',';
            }
            else{
                b=true;
            }
            result+="\""+temp+"\"";
        }
        result+="]})";
        return result;
    }
    @GET
    @Path("/cities/{country}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCities(@PathParam("country") String country) {
        List<String> list= new MySQL().getCities(country);
        System.out.println(country);
        boolean b =false;
        String result="showCities({\"name\":[";
        for(String temp:list){
            if(b==true) {
                result+= ',';
            }
            else{
                b=true;
            }
            result+="\""+temp+"\"";
        }
        result+="]})";
        return result;
    }


    @POST
    @Path("/population")
    @Produces(MediaType.TEXT_PLAIN)
    public String getPopulation(City city){
        String population = new MySQL().getPopulation(city.name);
        return population;
    }
    public static class City{
        @JsonProperty("name")
        public String name;
    }
}