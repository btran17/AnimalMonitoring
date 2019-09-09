import java.util.ArrayList;
import java.util.Iterator;

import java.util.Optional; 
import java.util.Comparator; 

//6.85
import java.util.List;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.*;
/**
 * Monitor counts of different types of animal.
 * Sightings are recorded by spotters.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29 (imperative)
 */
public class AnimalMonitor 
{
    // Records of all the sightings of animals.
    private ArrayList<Sighting> sightings;
    // String[] animalNames = {"Mountain Gorilla", "Buffalo",
    // "Topi", "Elephant"}; 

    //6.85
    // public static Collector<Sightings, ?, R> toList();  

    /**
     * Create an AnimalMonitor.
     */
    public AnimalMonitor()
    {
        this.sightings = new ArrayList<>();
    }

    /**
     * Add the sightings recorded in the given filename to the current list.
     * @param filename A CSV file of Sighting records.
     */
    public void addSightings(String filename)
    {
        SightingReader reader = new SightingReader();
        sightings.addAll(reader.getSightings(filename));
    }

    /**
     * Print details of all the sightings.
     */
    public void printList()
    {
        for(Sighting record : sightings) {
            System.out.println(record.getDetails());
        }
    }

    /**
     * Print the details of all the sightings of the given animal.
     * @param animal The type of animal.
     */
    public void printSightingsOf(String animal)
    {
        // for(Sighting record : sightings) {
        // if(animal.equals(record.getAnimal())) {
        // System.out.println(record.getDetails());
        // }
        // }

        //5.10
        sightings.stream()
        .filter(s -> animal.equals(s.getAnimal()))
        .forEach(s -> System.out.println(s.getDetails()));
    }

    //5.11
    /*
     * Print details of sightings on a particular period == dayID
     */
    public void printSightingDetails2(int period)
    {
        sightings.stream()
        .filter(sighting -> sighting.getPeriod() == period)
        .map(sighting -> sighting.getDetails())
        .forEach(details -> System.out.println(details));
    }

    //5.12
    public void printDetails3(String animal, int period)
    {
        sightings.stream()
            //5.13
        .filter(s -> animal.equals(s.getAnimal()))
        .filter(s -> period == s.getPeriod())
        .map(s -> s.getDetails())
        .forEach(details -> System.out.println(details));
    }

    public void printDetails3_2(String animal, int period)
    {
        sightings.stream()
            //5.13
        .filter(s -> period == s.getPeriod())
        .filter(s -> animal.equals(s.getAnimal()))
        .map(s -> s.getDetails())
        .forEach(details -> System.out.println(details));
    }

    /**
     * Print all the sightings by the given spotter.
     * @param spotter The ID of the spotter.
     */
    public void printSightingsBy(int spotter)
    {
        // for(Sighting record : sightings) {
        // if(record.getSpotter() == spotter) {
        // System.out.println(record.getDetails());
        // }
        // }        
        //5.14
        sightings.stream()
            //.map(sighting -> sighting.getDetails())
        .filter(sighting -> sighting.getSpotter() == spotter)
        .map(sighting -> sighting.getDetails())
            //.forEach(details -> System.out.println(details));
            //5.18
        .forEach(System.out::println);
    }

    //5.15
    public void printCountAllSightings(String animal)
    {
        sightings.stream()
        .filter(sighting -> animal.equals(sighting.getAnimal()))
        .map(sighting -> sighting.getCount())
        .forEach(count -> System.out.print(" " + count));
    }

    //5.20
    public void printDailySightings(String animal, int spotter, int period)
    {
        sightings.stream()
        .filter(sighting -> animal.equals(sighting.getAnimal()))
        .filter(sighting -> sighting.getSpotter() == spotter)
        .filter(sighting -> sighting.getPeriod() == period)
        .map(sighting -> sighting.getCount())
        .forEach(count -> System.out.print(" " + count));
    }

    //5.21
    public String printSeenAnimals(int spotter, int period)
    {
        return sightings.stream()
        .filter(s -> s.getSpotter() == spotter)
        .filter(s -> s.getPeriod() == period)
        .map(s -> s.getDetails())
        .reduce("", (animalName, detail) ->
                animalName + detail);
    }

    /**
     * Print a list of the types of animal considered to be endangered.
     * @param animalNames A list of animals names.
     * @param dangerThreshold Counts less-than or equal-to to this level
     *                        are considered to be dangerous.
     */
    public void printEndangered(ArrayList<String> animalNames, 
    int dangerThreshold)
    {
        // for(String animal : animalNames) {
        // if(getCount(animal) <= dangerThreshold) {
        // System.out.println(animal + " is endangered.");
        // }
        // }

        //5.17
        sightings.stream()
        .filter(s -> s.getCount() <= dangerThreshold)
        .forEach(s -> System.out.println(s.getAnimal() + " is endangered."));
    }

    /**
     * Return a count of the number of sightings of the given animal.
     * @param animal The type of animal.
     * @return The count of sightings of the given animal.
     */
    public int getCount(String animal)
    {
        // int total = 0;
        // for(Sighting sighting : sightings) {
        // if(animal.equals(sighting.getAnimal())) {
        // total = total + sighting.getCount();
        // }
        // }
        // return total;

        //5.19
        return sightings.stream()
        .filter(sighting -> animal.equals(sighting.getAnimal()))
        .map(sighting -> sighting.getCount())
        .reduce(0, (total, count) -> total + count);
    }

    /**
     * Remove from the sightings list all of those records with
     * a count of zero.
     */
    public void removeZeroCounts()
    {
        // Iterator<Sighting> it = sightings.iterator();
        // while(it.hasNext()) {
        // Sighting record = it.next();
        // if(record.getCount() == 0) {
        // it.remove();
        // }
        // }

        //5.22
        sightings.removeIf(sighting -> sighting.getCount() == 0);
    }

    //5.23
    /*
     * Removes all records by given spotter
     */
    public void removeSpotter()
    {
        sightings.removeIf(s -> s.getSpotter() == 0);
    }

    //5.25
    /*
     * Returns count of how many sighting records made by given spotter
     */
    public long sightingRecords(int spotterID)
    {
        return sightings.stream().count();
    }

    //5.26
    /*
     * returns largest count for the given animal in a
     * single sighting record
     */
    public void maxCount(String animal)
    {
        // sightings.stream()
        // .filter(s -> s.getAnimal().equals(animal));
        // return max(animal);

        //.filter(s -> animal.equals(s.getAnimal()))
        //.maptoInt(s -> s.getCount())
        //.reduce(0, (total, count) -> count);

        //return sightings.stream().max(Sighting::compare).get();
        //return sightings.stream().max().get();

        // sightings.stream()
        // .max(Comparator.comparing(s -> s.getCount()))
        // .get();

        //returns max count of list
        Comparator<Sighting> comparator = 
            Comparator.comparing(Sighting::getCount);
        Sighting maximum = sightings.stream()
            .filter(s -> animal.equals(s.getAnimal()))
            .max(comparator)
            .get();
        System.out.println(maximum.getCount());

    }

    //5.27
    /*
     * Returns first sighting of given animal and spotter
     */
    public Sighting findFirstSighting(String animal, int spotter)
    {
        // return sightings.stream()
        // //.filter(s -> s.getAnimal().equals(animal))
        // .filter(s -> animal.equals(s.getAnimal()))
        // .filter(s -> s.getSpotter() == spotter)
        //.findFirst();
        //.ifPresent(s -> System.out.println(s));

        //finds first in the collection
        //return sightings.stream().findFirst().get();

        return sightings.stream()
        .filter(s -> s.getAnimal().equals(animal))
        .filter(s -> s.getSpotter() == spotter)
        .findFirst().get();
    }

    /**
     * Return a list of all sightings of the given type of animal
     * in a particular area.
     * @param animal The type of animal.
     * @param area The ID of the area.
     * @return A list of sightings.
     */
    public ArrayList<Sighting> getSightingsInArea(String animal, int area)
    {
        ArrayList<Sighting> records = new ArrayList<>();
        for(Sighting record : sightings) {
            if(animal.equals(record.getAnimal())) {
                if(record.getArea() == area) {
                    records.add(record);
                }
            }
        }
        return records;
    }

    /**
     * Return a list of all the sightings of the given animal.
     * @param animal The type of animal.
     * @return A list of all sightings of the given animal.
     */
    public ArrayList<Sighting> getSightingsOf(String animal)
    {
        ArrayList<Sighting> filtered = new ArrayList<>();
        for(Sighting record : sightings) {
            if(animal.equals(record.getAnimal())) {
                filtered.add(record);
            }
        }
        return filtered;
    }

    //5.5
    public void printList5()
    {
        sightings.forEach(
            (Sighting record) -> {
                System.out.println(record.getDetails());
            }
        );
    }

    //5.6
    public void printList6a()
    {
        sightings.forEach(
            (record) -> {
                System.out.println(record.getDetails());
            }
        );
    }

    public void printList6b()
    {
        sightings.forEach(record -> System.out.println(record.getDetails()));
    }

    //6.85
    /**
     * Return a list of all the sightings of the given animal.
     * @param animal The type of animal
     * @return A list of all sightings of the given animal
     */
    public List<Sighting> getSightingsOf2(String animal)
    {
        return sightings.stream()
        .filter(record -> animal.equals(record.getAnimal()))
            // .collect(Collectors.toList());
        .collect(toList());
    }

    /**
     * Return a list of all sightings of the given type of animal
     * in a particular area.
     * @param animal The type of animal.
     * @param area The ID of the area.
     * @return A list of sightings.
     */
    public List<Sighting> getSightingsInArea2(String animal, int area)
    {
        //6.86
        return sightings.stream()
        .filter(record -> animal.equals(record.getAnimal()))
        .filter(record -> record.getArea() == area)
        // .collect(Collectors.toList());
        .collect(toList());
    }

}
