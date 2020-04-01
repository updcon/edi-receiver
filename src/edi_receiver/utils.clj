(ns edi-receiver.utils
  (:require [clojure.string :as string])
  (:import (java.io StringWriter)
           (java.net DatagramSocket InetAddress)))


(defn map-vals [f m]
  (into {} (for [[k v] m] [k (f v)])))


(defn map-keys [f m]
  (into {} (for [[k v] m] [(f k) v])))


(defn split-comma-separated [s]
  (->> (string/split s #",")
       (map string/trim)))


(defn pretty [& args]
  (let [out (StringWriter.)]
    (doseq [arg args]
      (clojure.pprint/pprint arg out))
    (.toString out)))


(defn get-host-ip []
  (-> (doto (DatagramSocket.)
        (.connect (InetAddress/getByName "8.8.8.8") 10002))
      (.getLocalAddress) (.getHostAddress)))
