package com.softserveinc.tender.service.impl;

import com.softserveinc.tender.entity.Tender;
import com.softserveinc.tender.entity.TenderStatus;
import com.softserveinc.tender.entity.template.Roles;
import com.softserveinc.tender.repo.TenderFilter;
import com.softserveinc.tender.repo.TenderRepository;
import com.softserveinc.tender.service.ProfileService;
import com.softserveinc.tender.service.TenderService;
import com.softserveinc.tender.service.TenderStatusService;
import com.softserveinc.tender.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TenderServiceImpl implements TenderService {

    @Autowired
    private TenderRepository tenderRepository;

    @Autowired
    private TenderStatusService tenderStatusService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    public Tender findOne(int id) {
        return tenderRepository.findOne(id);
    }

    @Override
    public List<Tender> findByCustomParameters(TenderFilter tenderFilter, Pageable pageable) {
        setProfileIdIntoFilter(tenderFilter);
        return tenderRepository.findByCustomParameters(tenderFilter.getTenderTitle(), tenderFilter.getMinPrice(),
                tenderFilter.getMaxPrice(),
                tenderFilter.getStatuses(), tenderFilter.getCategories(),
                tenderFilter.getLocations(), tenderFilter.getItems(),
                tenderFilter.getMinDate(), tenderFilter.getMaxDate(),
                tenderFilter.getCategoryFlag(), tenderFilter.getItemFlag(),
                tenderFilter.getLocationFlag(), tenderFilter.getStatusFlag(),
                tenderFilter.getPriceFlag(), tenderFilter.getSearchFlag(),
                tenderFilter.getCustomerTendersFlag(), tenderFilter.getSellerTendersFlag(), tenderFilter.getProfileId(),
                pageable);
    }

    @Override
    public Long getTendersNumber(TenderFilter tenderFilter) {
        setProfileIdIntoFilter(tenderFilter);
        return tenderRepository.getTendersNumber(tenderFilter.getTenderTitle(), tenderFilter.getMinPrice(),
                tenderFilter.getMaxPrice(),
                tenderFilter.getStatuses(), tenderFilter.getCategories(),
                tenderFilter.getLocations(), tenderFilter.getItems(),
                tenderFilter.getMinDate(), tenderFilter.getMaxDate(),
                tenderFilter.getCategoryFlag(), tenderFilter.getItemFlag(),
                tenderFilter.getLocationFlag(), tenderFilter.getStatusFlag(),
                tenderFilter.getPriceFlag(),tenderFilter.getSearchFlag(),
                tenderFilter.getCustomerTendersFlag(), tenderFilter.getSellerTendersFlag(), tenderFilter.getProfileId());
    }

    @Override
    public Tender updateTender(Integer tenderId, String statusName, Date endDate, String description) {
        Tender tender = tenderRepository.findOne(tenderId);
        TenderStatus tenderStatus = tenderStatusService.findByName(statusName);

        tender.setStatus(tenderStatus);
        if (endDate!=null)tender.setEndDate(endDate);
        tender.setDescription(description);
        return tenderRepository.save(tender);
    }

    @Override
    public List<Tender> findAll() {
        return tenderRepository.findAll();
    }

    @Override
    public Tender save(Tender tender) {
        return tenderRepository.saveAndFlush(tender);
    }

    private TenderFilter setProfileIdIntoFilter(TenderFilter tenderFilter) {
        if (tenderFilter.getUserRole() != null && tenderFilter.getUserRole().equals(Roles.SELLER.toString())) {
            tenderFilter.setProfileId(userService.findByLogin(SecurityContextHolder.getContext()
                    .getAuthentication().getName()).getId());
        } else if (tenderFilter.getUserRole() != null && tenderFilter.getUserRole().equals(Roles.CUSTOMER.toString())) {
            tenderFilter.setProfileId(profileService.findProfileByUserLogin(SecurityContextHolder.getContext()
                    .getAuthentication().getName()).getId());
        }
        return tenderFilter;
    }
}
