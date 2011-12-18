(definem cond lst 
	(if (nil? lst) nil 
		(list 'if 
			  (first (first lst)) 
			  (cons 'do (rest (first lst))) 
			  (cons 'cond (rest lst))
		)
	)
)