package com.prueba;

import com.prueba.carro.Car;
import com.prueba.carro.SalesReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class CasoServiceImpl implements CasoService{

    private static final String COLLECTION = "cars";

//    @Autowired
//    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void agregate(int hasta,int anno) {

        for (int i = 0; i < hasta; i++) {
            System.out.println("--------------Sales Report :"+i+"-------------------------------");


            List<SalesReport> byYear = aggregationByYear2(anno);

            for (SalesReport salesReport : byYear) {
                System.out.println(salesReport);
            }
        }
        System.out.println("--------------FIN-------------------------------");
    }

    public void create(Car car) {
        if (car != null) {
            this.mongoTemplate.insert(car, COLLECTION);
        }
    }


//    public List<SalesReport> aggregationByYear(int year) {
//        Aggregation aggregation = newAggregation(
//                match(Criteria.where("salesyear").is(year)),
//                group("brand","salesyear").sum("numberOfCars").as("total"),
//                sort(Sort.Direction.ASC, previousOperation(), "brand")
//        ).withOptions(AggregationOptions.builder().allowDiskUse(true).build());
//
//        Flux<SalesReport> groupResults = reactiveMongoTemplate.aggregate(
//                aggregation, Car.class, SalesReport.class);
//
//        List<SalesReport> salesReport = groupResults.collectList().block();
//
//        return salesReport;
//    }

    public List<SalesReport> aggregationByYear2(int year) {
        Aggregation aggregation = newAggregation(
                match(Criteria.where("salesyear").is(year)),
                group("brand","salesyear").sum("numberOfCars").as("total"),
                sort(Sort.Direction.ASC, previousOperation(), "brand")
        );//.withOptions(AggregationOptions.builder().allowDiskUse(true).build());

        AggregationResults<SalesReport> groupResults = mongoTemplate.aggregate(
                aggregation, Car.class, SalesReport.class);

        List<SalesReport> salesReport = groupResults.getMappedResults();

        return salesReport;
    }

    @Override
    public void insertar(int hasta) {


        for (int i = 0; i < hasta; i++) {


                System.out.println("--------------Grupo :"+i+"-------------------------------");

                Car polo2014 = new Car();
                polo2014.setBrand("Volkswagen");
                polo2014.setModel("Polo");
                polo2014.setNumberOfCars(59600);
                polo2014.setSalesyear(2004);
                create(polo2014);

                Car polo2015 = new Car();
                polo2015.setBrand("Volkswagen");
                polo2015.setModel("Polo");
                polo2015.setNumberOfCars(29010);
                polo2015.setSalesyear(2005);
                create(polo2015);

                Car jetta2014 = new Car();
                jetta2014.setBrand("Volkswagen");
                jetta2014.setModel("Jetta");
                jetta2014.setNumberOfCars(25000);
                jetta2014.setSalesyear(2004);
                create(jetta2014);

                Car jetta2015 = new Car();
                jetta2015.setBrand("Volkswagen");
                jetta2015.setModel("Jetta");
                jetta2015.setNumberOfCars(16200);
                jetta2015.setSalesyear(2005);
                create(jetta2015);

                Car swift2014 = new Car();
                swift2014.setBrand("Maruti Suzuki");
                swift2014.setModel("Swift");
                swift2014.setNumberOfCars(168000);
                swift2014.setSalesyear(2020);
                create(swift2014);

                Car swift2015 = new Car();
                swift2015.setBrand("Maruti Suzuki");
                swift2015.setModel("Swift");
                swift2015.setNumberOfCars(118000);
                swift2015.setSalesyear(2019);
                create(swift2015);

                Car ertiga2014 = new Car();
                ertiga2014.setBrand("Maruti Suzuki");
                ertiga2014.setModel("Ertiga");
                ertiga2014.setNumberOfCars(80000);
                ertiga2014.setSalesyear(2014);
                create(ertiga2014);

                Car ertiga2015 = new Car();
                ertiga2015.setBrand("Maruti Suzuki");
                ertiga2015.setModel("Ertiga");
                ertiga2015.setNumberOfCars(42000);
                ertiga2015.setSalesyear(2014);
                create(ertiga2015);

                Car i202014 = new Car();
                i202014.setBrand("Hyundai");
                i202014.setModel("i20");
                i202014.setNumberOfCars(45000);
                i202014.setSalesyear(2014);
                create(i202014);

                Car i202015 = new Car();
                i202015.setBrand("Hyundai");
                i202015.setModel("i20");
                i202015.setNumberOfCars(19000);
                i202015.setSalesyear(2014);
                create(i202015);

                Car i102014 = new Car();
                i102014.setBrand("Hyundai");
                i102014.setModel("i10");
                i102014.setNumberOfCars(95000);
                i102014.setSalesyear(2014);
                create(i102014);

                Car i102015 = new Car();
                i102015.setBrand("Hyundai");
                i102015.setModel("i10");
                i102015.setNumberOfCars(55000);
                i102015.setSalesyear(2014);
                create(i102015);

            }

        System.out.println("--------------FIN-------------------------------");
    }
}
