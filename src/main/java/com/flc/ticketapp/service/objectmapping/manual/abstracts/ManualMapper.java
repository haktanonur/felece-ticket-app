package com.flc.ticketapp.service.objectmapping.manual.abstracts;

import com.flc.ticketapp.service.objectmapping.abstraction.CollectionMapper;
import com.flc.ticketapp.service.objectmapping.abstraction.Mapper;

public interface ManualMapper<Source, Destination> extends Mapper<Source, Destination>, CollectionMapper<Source, Destination> {
}
