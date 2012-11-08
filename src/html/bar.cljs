(ns holyshit3
  (:use
    [flapjax.core :only [sync-e]]
    [mytest.ui    :only [make-radio make-tabs]])
  (:use-macros
    [hlisp.macros :only [def-values deftpl tpl]]))

(defn pad-text
  [e]
  ($text (str " " e " ")))

(def a-void (a {:href "javascript:void(0)"}))

(defn q-and-a
  [q a]
  (let [[mytabs question-tab question answer-tab answer]
        (make-tabs "one" li div "one" li div "two")
        [myradio show-question show-answer]
        (make-radio "one" a-void "one" a-void "two")]
    (sync-e myradio mytabs)
    (with-meta
      (div
        (ul {:class "control-tabs"}
          (question-tab "question")
          (answer-tab "answer"))
        (div
          (question
            (p (b "Q.") (pad-text q) (show-answer "A")))
          (answer
            (p (b "A.") (pad-text a) (show-question "Q")))))
      {:tabs mytabs})))

(def q1
  (q-and-a
    "Why did the chicken cross the road?"
    "To get to the other side!")) 

(def q2
  (q-and-a
    "Why?"
    "Because.")) 

(sync-e (:tabs (meta q1)) (:tabs (meta q2)))

;;===========================================================================;;

(html

  (head
    (title "another test")
    (style {:type "text/css"}
      "ul.control-tabs { padding: 0; list-style-type: none; }                          
       ul.control-tabs li { display: inline; padding-right: 5px; }                     
       ul.control-tabs li.checked { color: red; }"))

  (body
    (js/alert "hello bar")

    (div
      (h2 "Testing")
      (ol
        (li (q1 {:id "q1"})) 
        (li (q2 {:id "q2"})))))) 



