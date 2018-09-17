package service;

import entity.Flower;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractFlowerBuilder {
    protected Set<Flower> flower;

    public AbstractFlowerBuilder() {
        flower = new HashSet<Flower>();
    }

    public Set<Flower> getFlowers() {
        return flower;
    }

    abstract public void buildSetFlowers(String fileName);
}