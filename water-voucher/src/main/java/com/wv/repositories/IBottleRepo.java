package com.wv.repositories;

import java.util.List;

import com.wv.model.Bottle;

public interface IBottleRepo extends IGlobalRepo<Bottle> {

    public List<Bottle> searchBottlesByCriteria(Bottle bottle);
}
