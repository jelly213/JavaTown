package com.backendjavatown.services.dto;

import com.backendjavatown.models.Customer;
import com.backendjavatown.models.Fine;
import com.backendjavatown.models.Loan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto extends UsersDto {
    private List<Long> loanIds;
    private List<Long> fineIds;
    private int activeLoanCount;
    private double totalFineAmount;

    public CustomerDto(Long id, String firstName, String lastName, String email,
                       List<Long> loanIds, List<Long> fineIds, int activeLoanCount, double totalFineAmount) {
        super(id, firstName, lastName, email);
        this.loanIds = loanIds;
        this.fineIds = fineIds;
        this.activeLoanCount = activeLoanCount;
        this.totalFineAmount = totalFineAmount;
    }

    public static CustomerDto toDto(Customer customer) {
        List<Long> loanIds = customer.getLoans().stream()
                .map(Loan::getId)
                .collect(Collectors.toList());

        List<Long> fineIds = customer.getFines().stream()
                .map(Fine::getId)
                .collect(Collectors.toList());

        int activeLoanCount = customer.getLoans().size();

        double totalFineAmount = customer.getFines().stream()
                .filter(fine -> !fine.isStatus()) // unpaid fines
                .mapToDouble(Fine::getAmount)
                .sum();

        return new CustomerDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                loanIds,
                fineIds,
                activeLoanCount,
                totalFineAmount
        );
    }
}