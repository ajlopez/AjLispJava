(definem and lst
		(if (nil? lst) true 
			(list 'cond 
				  (list (first lst) (cons 'and (rest lst))) 
				  '(true false)
			)
		)
)