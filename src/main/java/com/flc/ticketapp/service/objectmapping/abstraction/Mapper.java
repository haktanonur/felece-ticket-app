package com.flc.ticketapp.service.objectmapping.abstraction;

@FunctionalInterface
public interface Mapper<Source, Destination> {

    Destination map(Source source);

}
