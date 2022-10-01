package com.app.finance.mapper;

import com.app.finance.entity.PersonalLoan;
import com.app.finance.entity.PersonalLoan.PersonalLoanBuilder;
import com.app.finance.entity.PersonalLoanInstallmentsDtls;
import com.app.finance.entity.PersonalLoanInstallmentsDtls.PersonalLoanInstallmentsDtlsBuilder;
import com.app.finance.model.request.PersonalLoanDtlsReq;
import com.app.finance.model.request.PersonalLoanInstallmentDtlReq;
import com.app.finance.model.response.PersonalLoanAccountResponse;
import com.app.finance.model.response.PersonalLoanAccountResponse.PersonalLoanAccountResponseBuilder;
import com.app.finance.model.response.PersonalLoanInstallmentDtlResp;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-01T15:52:14+0530",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.50.v20210914-1429, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@Component
public class PersonalLoanDtlMapperImpl implements PersonalLoanDtlMapper {

    @Override
    public PersonalLoan map(PersonalLoanDtlsReq request) {
        if ( request == null ) {
            return null;
        }

        PersonalLoanBuilder personalLoan = PersonalLoan.builder();

        personalLoan.loanAmt( request.getLoanAmt() );
        personalLoan.loanEndDate( request.getLoanEndDate() );
        personalLoan.loanFrom( request.getLoanFrom() );
        personalLoan.loanId( request.getLoanId() );
        personalLoan.loanInterest( request.getLoanInterest() );
        personalLoan.loanStartDate( request.getLoanStartDate() );
        personalLoan.loanStatus( request.getLoanStatus() );
        personalLoan.loanTenure( request.getLoanTenure() );
        personalLoan.monthlyEMIAmt( request.getMonthlyEMIAmt() );
        personalLoan.montlyEMIDate( request.getMontlyEMIDate() );
        personalLoan.remark( request.getRemark() );

        return personalLoan.build();
    }

    @Override
    public PersonalLoanInstallmentsDtls map(PersonalLoanInstallmentDtlReq request) {
        if ( request == null ) {
            return null;
        }

        PersonalLoanInstallmentsDtlsBuilder personalLoanInstallmentsDtls = PersonalLoanInstallmentsDtls.builder();

        personalLoanInstallmentsDtls.loanId( personalLoanInstallmentDtlReqToPersonalLoan( request ) );
        personalLoanInstallmentsDtls.installmentAmt( request.getInstallmentAmt() );
        personalLoanInstallmentsDtls.installmentDate( request.getInstallmentDate() );
        personalLoanInstallmentsDtls.installmentId( request.getInstallmentId() );
        personalLoanInstallmentsDtls.installmentType( request.getInstallmentType() );

        return personalLoanInstallmentsDtls.build();
    }

    @Override
    public PersonalLoanAccountResponse map(PersonalLoan request) {
        if ( request == null ) {
            return null;
        }

        PersonalLoanAccountResponseBuilder personalLoanAccountResponse = PersonalLoanAccountResponse.builder();

        personalLoanAccountResponse.loanAmt( request.getLoanAmt() );
        personalLoanAccountResponse.loanEndDate( request.getLoanEndDate() );
        personalLoanAccountResponse.loanFrom( request.getLoanFrom() );
        personalLoanAccountResponse.loanId( request.getLoanId() );
        personalLoanAccountResponse.loanInterest( request.getLoanInterest() );
        personalLoanAccountResponse.loanStartDate( request.getLoanStartDate() );
        personalLoanAccountResponse.loanStatus( request.getLoanStatus() );
        personalLoanAccountResponse.loanTenure( request.getLoanTenure() );
        personalLoanAccountResponse.monthlyEMIAmt( request.getMonthlyEMIAmt() );
        personalLoanAccountResponse.montlyEMIDate( request.getMontlyEMIDate() );
        personalLoanAccountResponse.remark( request.getRemark() );

        return personalLoanAccountResponse.build();
    }

    @Override
    public List<PersonalLoanInstallmentDtlResp> map(List<PersonalLoanInstallmentsDtls> request) {
        if ( request == null ) {
            return null;
        }

        List<PersonalLoanInstallmentDtlResp> list = new ArrayList<PersonalLoanInstallmentDtlResp>( request.size() );
        for ( PersonalLoanInstallmentsDtls personalLoanInstallmentsDtls : request ) {
            list.add( map( personalLoanInstallmentsDtls ) );
        }

        return list;
    }

    protected PersonalLoan personalLoanInstallmentDtlReqToPersonalLoan(PersonalLoanInstallmentDtlReq personalLoanInstallmentDtlReq) {
        if ( personalLoanInstallmentDtlReq == null ) {
            return null;
        }

        PersonalLoanBuilder personalLoan = PersonalLoan.builder();

        personalLoan.loanId( personalLoanInstallmentDtlReq.getLoanId() );

        return personalLoan.build();
    }
}
