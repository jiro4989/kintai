(ns server.database
  (:require [clj-postgresql.core :as pg]
            [clojure.java.jdbc :as jdbc]))

; The pg/spec and pg/pool functions use PGHOST, PGPORT, PGUSER and PGDATABASE
; environment variables and the ~/.pgpass file by default. The function
; arguments can be used to override the connection parameters in the
; environment. E.g.:

(def db (pg/spec))

(defn insert-signup [email password]
  (jdbc/insert! db :member [:email :password] [email password]))

(defn select-member [email]
  (jdbc/query db ["SELECT * FROM member WHERE email = ?" email]))

; (insert-signup "sushi" "morimori")
(select-member "unko")
