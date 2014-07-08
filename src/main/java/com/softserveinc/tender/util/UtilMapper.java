package com.softserveinc.tender.util;

import com.softserveinc.tender.dto.BidDto;
import com.softserveinc.tender.dto.CategoryDto;
import com.softserveinc.tender.dto.ConflictDto;
import com.softserveinc.tender.dto.FeedbackDto;
import com.softserveinc.tender.dto.ProposalDto;
import com.softserveinc.tender.dto.TenderDto;
import com.softserveinc.tender.dto.UnitDto;
import com.softserveinc.tender.entity.Bid;
import com.softserveinc.tender.entity.Category;
import com.softserveinc.tender.entity.Conflict;
import com.softserveinc.tender.entity.Feedback;
import com.softserveinc.tender.entity.Location;
import com.softserveinc.tender.entity.Profile;
import com.softserveinc.tender.entity.Proposal;
import com.softserveinc.tender.entity.Tender;
import com.softserveinc.tender.entity.Unit;
import com.softserveinc.tender.service.DealService;
import com.softserveinc.tender.service.ProfileService;
import com.softserveinc.tender.service.ProposalService;
import com.softserveinc.tender.service.UserService;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Condition;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UtilMapper implements Convertible {
    private ModelMapper nativeModelMapper = new ModelMapper();

    @Autowired
    private DealService dealService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProposalService proposalService;

    public UtilMapper() {

        final Converter<Profile, String> toFullName = new AbstractConverter<Profile, String>() {
            @Override
            protected String convert(Profile source) {
                return source.getFirstName() + " " + source.getLastName();
            }
        };

        //Mapping for Bid to BidDto
        nativeModelMapper.addMappings(new PropertyMap<Bid, BidDto>() {
            @Override
            protected void configure() {
                map().setBidId(source.getId());
            }
        });

        //Mapping for Proposal to ProposalDto
        nativeModelMapper.addMappings(new PropertyMap<Proposal, ProposalDto>() {
            Converter<List<Bid>, Double> toTotalBidPrice = new AbstractConverter<List<Bid>, Double>() {
                @Override
                protected Double convert(List<Bid> source) {
                    final Integer START_COUNT_VALUE = 0;
                    BigDecimal totalBidsSum = new BigDecimal(START_COUNT_VALUE);
                    for (Bid bid : source) {
                        totalBidsSum = totalBidsSum.add(bid.getPrice());
                    }

                    return totalBidsSum.doubleValue();
                }
            };

            Converter<Integer, Boolean> toHaveDeals = new AbstractConverter<Integer, Boolean>() {
                @Override
                protected Boolean convert(Integer source) {
                    return dealService.findByProposalId(source).size() > 0;
                }
            };

            Converter<List<Bid>, List<BidDto>> toBidDto = new AbstractConverter<List<Bid>, List<BidDto>>() {
                @Override
                protected List<BidDto> convert(List<Bid> source) {
                    return mapObjects(source, BidDto.class);
                }
            };

            @Override
            protected void configure() {
                map().setNumberOfBids(source.getBids().size());
                using(toFullName).map(source.getSeller().getProfile()).setFullName(null);
                using(toTotalBidPrice).map(source.getBids()).setTotalBidsPrice(null);
                using(toHaveDeals).map(source.getId()).setHaveDeals(null);
                using(toBidDto).map(source.getBids()).setBidDtos(null);
            }
        });

        //Mapping for Tender to TenderDto
        nativeModelMapper.addMappings(new PropertyMap<Tender, TenderDto>() {

            Converter<List<Location>, List<String>> toLocations = new AbstractConverter<List<Location>, List<String>>() {
                @Override
                protected List<String> convert(List<Location> source) {
                    List<String> locations = new ArrayList<>();
                    for (Location location : source) {
                        locations.add(location.getName());
                    }
                    return locations;
                }
            };

            Converter<List<Unit>, Set<String>> toCategories = new AbstractConverter<List<Unit>, Set<String>>() {
                @Override
                protected Set<String> convert(List<Unit> source) {
                    Set<String> categories = new HashSet<>();
                    for (Unit unit : source) {
                        categories.add(unit.getItem().getCategory().getName());
                    }
                    return categories;
                }
            };

            Converter<Tender, Integer> toUserId = new AbstractConverter<Tender, Integer>() {
                @Override
                protected Integer convert(Tender source) {
                    return userService.findByLogin(Util.getUserLogin()).getId();
                }
            };

            Converter<Tender, Boolean> toHaveNewProposal = new AbstractConverter<Tender, Boolean>() {
                @Override
                protected Boolean convert(Tender source) {
                    return source.getAuthor().equals(profileService.findProfileByUserLogin(Util.getUserLogin())) &&
                            proposalService.getTenderNewProposalsForCustomer(source.getId()) > 0;
                }
            };

            Condition isNotAnonymousUser = new Condition() {
                @Override
                public boolean applies(MappingContext context) {
                    return !Util.getUserLogin().equals(Constants.UNKNOWN_USER);
                }
            };

            @Override
            protected void configure() {
                using(toFullName).map(source.getAuthor()).setAuthorName(null);
                map().setStatus(source.getStatus().getName());
                using(toLocations).map(source.getLocations()).setLocations(null);
                using(toCategories).map(source.getUnits()).setCategories(null);
                when(Conditions.isNotNull()).map().setProposals(source.getProposals().size());
                when(Conditions.isNotNull()).map().setDescription(source.getDescription());
                map().setAuthorId(source.getAuthor().getUser().getId());
                when(isNotAnonymousUser).using(toUserId).map(source).setUserId(null);
                when(isNotAnonymousUser).using(toHaveNewProposal).map(source).setHaveNewProposal(null);
            }
        });

        //Mapping for Unit to UnitDto
        nativeModelMapper.addMappings(new PropertyMap<Unit, UnitDto>() {
            Converter<Integer, Boolean> toHaveDeals = new AbstractConverter<Integer, Boolean>() {
                @Override
                protected Boolean convert(Integer source) {
                    return dealService.findByUnitId(source).size() > 0;
                }
            };

            @Override
            protected void configure() {
                map().setUnitName(source.getItem().getName());
                map().setCategoryName(source.getItem().getCategory().getName());
                map().setNumberOfBids(source.getBids().size());
                using(toHaveDeals).map(source.getId()).setHaveDeals(null);
            }
        });

        //Mapping for Category to CategoryDto
        nativeModelMapper.addMappings(new PropertyMap<Category, CategoryDto>() {
            @Override
            protected void configure() {
                when(Conditions.isNotNull()).map().setParentId(source.getParent().getId());
            }
        });

        //Mapping for Conflict to ConflictDto
        nativeModelMapper.addMappings(new PropertyMap<Conflict, ConflictDto>() {
            @Override
            protected void configure() {
                map().setTitle(source.getBid().getProposal().getTender().getTitle());
                map().setStatus(source.getStatus().getName());
                using(toFullName).map(source.getBid().getProposal().getTender().getAuthor()).setCustomerName(null);
                using(toFullName).map(source.getBid().getProposal().getSeller().getProfile()).setSellerName(null);
            }
        });
    }

    @Override
    public <S, D> D mapObject(S source, Class<D> destinationType) {
        return nativeModelMapper.map(source, destinationType);
    }

    @Override
    public <S, D> List<D> mapObjects(List<S> source, Class<D> destinationType) {
        List<D> result = new ArrayList<>();
        for (S object : source) {
            result.add(nativeModelMapper.map(object, destinationType));
        }
        return result;
    }


}
