CREATE TABLE accounts (
                          id              UUID PRIMARY KEY,
                          account_number  VARCHAR(20)    NOT NULL UNIQUE,
                          owner_id        UUID           NOT NULL,
                          currency        CHAR(3)        NOT NULL,
                          balance         NUMERIC(19,4)  NOT NULL DEFAULT 0,
                          status          VARCHAR(20)    NOT NULL,
                          version         BIGINT         NOT NULL DEFAULT 0,
                          created_at      TIMESTAMPTZ    NOT NULL DEFAULT now(),
                          CONSTRAINT chk_balance_non_negative CHECK (balance >= 0)
);

CREATE TABLE ledger_entries (
                                id            UUID PRIMARY KEY,
                                account_id    UUID           NOT NULL REFERENCES accounts(id),
                                entry_type    VARCHAR(10)    NOT NULL CHECK (entry_type IN ('CREDIT', 'DEBIT')),
                                amount        NUMERIC(19,4)  NOT NULL CHECK (amount > 0),
                                reference_id  UUID           NOT NULL,
                                created_at    TIMESTAMPTZ    NOT NULL DEFAULT now()
);

CREATE INDEX idx_ledger_account_created
    ON ledger_entries (account_id, created_at DESC);