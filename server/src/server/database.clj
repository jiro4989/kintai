(ns server.database
  (:require [clj-postgresql.core :as pg]
            [clojure.java.jdbc :as jdbc]))

; The pg/spec and pg/pool functions use PGHOST, PGPORT, PGUSER and PGDATABASE
; environment variables and the ~/.pgpass file by default. The function
; arguments can be used to override the connection parameters in the
; environment. E.g.:

(def db (pg/spec))

(defn uuid []
  (str (java.util.UUID/randomUUID)))

(defn now []
  (java.sql.Timestamp/valueOf (.format (java.text.SimpleDateFormat. "yyyy-MM-dd HH:mm:ss") (java.util.Date.))))

(defn insert-member [email password]
  (jdbc/insert! db :member [:id :email :password :created_at :updated_at] [(uuid) email password (now) (now)]))
(defn select-member [email]
  (jdbc/query db ["SELECT * FROM member WHERE email = ?" email]))

(defn select-members []
  (jdbc/query db ["SELECT * FROM member"]))

; (insert-signup "sushi" "morimori")
; (select-member "unko")
