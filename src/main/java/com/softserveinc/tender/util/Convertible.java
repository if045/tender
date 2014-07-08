package com.softserveinc.tender.util;

import java.util.List;

public interface Convertible {
    <S, D> D mapObject(final S source, final Class<D> destinationType);
    <S, D> List<D> mapObjects(final List<S> source, final Class<D> destinationType);
}
