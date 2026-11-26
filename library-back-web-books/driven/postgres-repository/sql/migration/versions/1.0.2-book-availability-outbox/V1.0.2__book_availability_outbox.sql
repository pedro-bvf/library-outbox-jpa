CREATE TABLE book_availability_outbox (
  id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  public_id bigint NOT NULL,
  event_type varchar(20) NOT NULL,
  processed_at timestamptz
);

CREATE INDEX idx_book_availability_outbox_pending
  ON book_availability_outbox (id)
  WHERE processed_at IS NULL;
