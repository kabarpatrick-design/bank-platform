package com.patrick.bank.account.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "ledger_entries")
public class LedgerEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id")
    private Account account;
    @Enumerated(EnumType.STRING)
    @Column(name = "entry_type", nullable = false, length = 10)
    private EntryType entryType;
    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;
    @Column(name = "reference_id", nullable = false)
    private UUID referenceId;
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    protected LedgerEntry(){

    }

    public LedgerEntry(Account account, EntryType entryType,BigDecimal amount,UUID referenceId) {
        this.account = account;
        this.entryType = entryType;
        this.amount = amount;
        this.referenceId = referenceId;
        this.createdAt = Instant.now();
    }

    public Account getAccount() {
        return account;
    }

    public UUID getId() {
        return id;
    }

    public EntryType getEntryType() {
        return entryType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public UUID getReferenceId() {
        return referenceId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
