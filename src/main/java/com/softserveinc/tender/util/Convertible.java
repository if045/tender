package com.softserveinc.tender.util;

import java.util.List;

public interface Convertible {
    //<S, D> D mapObject(S source, D destination);
    <S, D> D mapObject(final S source, final Class<D> destinationType);
    <S, D> List<D> mapObjects(List<S> source, D destination);
}
