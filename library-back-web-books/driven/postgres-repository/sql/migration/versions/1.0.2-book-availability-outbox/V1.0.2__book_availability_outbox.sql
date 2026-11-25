CREATE TABLE book_availability_outbox (
  id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  public_id bigint NOT NULL,
  available boolean NOT NULL,
  tombstone boolean NOT NULL DEFAULT false,
  processed_at timestamptz
);

CREATE INDEX idx_book_availability_outbox_pending
  ON book_availability_outbox (id)
  WHERE processed_at IS NULL;
