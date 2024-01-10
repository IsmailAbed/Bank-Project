use abedbank;


CREATE INDEX sender ON transactions(sender);

CREATE INDEX receiver ON transactions(receiver);
