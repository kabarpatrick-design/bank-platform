package com.patrick.bank.account.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "account_number", nullable = false, unique = true, length = 30)
    private String accountNumber;
    @Column(name = "owner_id", nullable = false)
    private UUID ownerId;
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(nullable = false,length = 3)
    private String currency;
    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private AccountStatus status;
    @Version
    private Long version;
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
    protected Account(){

    }

    public Account(String accountNumber, UUID ownerId, String currency) {
        this.accountNumber = accountNumber;
        this.ownerId = ownerId;
        this.currency = currency;
        this.status = AccountStatus.ACTIVE;
        this.createdAt = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public Long getVersion() {
        return version;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
