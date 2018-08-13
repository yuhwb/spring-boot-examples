package com.yuhw.project.xxljob.wen.tests.yaml;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Iterator;

public class YamlTest1 {
    public static void main(String[] args){
        Yaml yaml = new Yaml();
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config/study3.yaml");
        Iterable<Object> objectIterator = yaml.loadAll(inputStream);
        Iterator<Object> iterator =  objectIterator.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
