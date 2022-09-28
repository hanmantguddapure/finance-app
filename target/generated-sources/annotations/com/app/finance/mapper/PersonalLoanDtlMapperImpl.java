package com.app.finance.mapper;

import com.app.finance.entity.PersonalLoan;
import com.app.finance.entity.PersonalLoan.PersonalLoanBuilder;
import com.app.finance.entity.PersonalLoanInstallmentsDtls;
import com.app.finance.entity.PersonalLoanInstallmentsDtls.PersonalLoanInstallmentsDtlsBuilder;
import com.app.finance.model.request.PersonalLoanDtlsReq;
import com.app.finance.model.request.PersonalLoanInstallmentDtlReq;
import com.app.finance.model.response.PersonalLoanAccountResponse;
import com.app.finance.model.response.PersonalLoanAccountResponse.PersonalLoanAccountResponseBuilder;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-28T17:24:08+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_321 (Oracle Corporation)"
)
@Component
public class PersonalLoanDtlMapperImpl implements PersonalLoanDtlMapper {

    @Override
    public PersonalLoan mapToEntity(PersonalLoanDtlsReq request) {
        if ( request == null ) {
            return null;
        }

        PersonalLoanBuilder personalLoan = PersonalLoan.builder();

        personalLoan.loanId( request.getLoanId() );
        personalLoan.loanFrom( request.getLoanFrom() );
        personalLoan.loanAmt( request.getLoanAmt() );
        personalLoan.monthlyEMIAmt( request.getMonthlyEMIAmt() );
        personalLoan.loanTenure( request.getLoanTenure() );
        personalLoan.montlyEMIDate( request.getMontlyEMIDate() );
        personalLoan.loanStartDate( request.getLoanStartDate() );
        personalLoan.loanEndDate( request.getLoanEndDate() );
        personalLoan.loanInterest( request.getLoanInterest() );
        personalLoan.remark( request.getRemark() );
        personalLoan.loanStatus( request.getLoanStatus() );

        return personalLoan.build();
    }

    @Override
    public PersonalLoanInstallmentsDtls mapToPersonalLoanInstallmentEntity(PersonalLoanInstallmentDtlReq request) {
        if ( request == null ) {
            return null;
        }

        PersonalLoanInstallmentsDtlsBuilder personalLoanInstallmentsDtls = PersonalLoanInstallmentsDtls.builder();

        personalLoanInstallmentsDtls.installmentId( request.getInstallmentId() );
        personalLoanInstallmentsDtls.installmentAmt( request.getInstallmentAmt() );
        personalLoanInstallmentsDtls.installmentType( request.getInstallmentType() );
        personalLoanInstallmentsDtls.installmentDate( request.getInstallmentDate() );

        return personalLoanInstallmentsDtls.build();
    }

    @Override
    public PersonalLoanAccountResponse mapToResponse(PersonalLoan request) {
        if ( request == null ) {
            return null;
        }

        PersonalLoanAccountResponseBuilder personalLoanAccountResponse = PersonalLoanAccountResponse.builder();

        personalLoanAccountResponse.loanId( request.getLoanId() );
        personalLoanAccountResponse.loanFrom( request.getLoanFrom() );
        personalLoanAccountResponse.loanAmt( request.getLoanAmt() );
        personalLoanAccountResponse.monthlyEMIAmt( request.getMonthlyEMIAmt() );
        personalLoanAccountResponse.loanTenure( request.getLoanTenure() );
        personalLoanAccountResponse.montlyEMIDate( request.getMontlyEMIDate() );
        personalLoanAccountResponse.loanStartDate( request.getLoanStartDate() );
        personalLoanAccountResponse.loanEndDate( request.getLoanEndDate() );
        personalLoanAccountResponse.loanInterest( request.getLoanInterest() );
        personalLoanAccountResponse.remark( request.getRemark() );
        personalLoanAccountResponse.loanStatus( request.getLoanStatus() );

        return personalLoanAccountResponse.build();
    }
}
