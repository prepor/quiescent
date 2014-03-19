(ns quiescent)

(defmacro defcomponent
  "Creates a ReactJS component with the given name, an (optional)
  docstring, an argument vector and a body which will be used as the
  rendering function to quiescent/component.

  Shorthand for:

  (def name (quiescent/component (fn [value] body)))"
  [name & forms]
  (let [has-docstr? (string? (first forms))
        docstr (if has-docstr? (first forms) "")
        argvec (if has-docstr? (second forms) (first forms))
        body (if has-docstr? (drop 2 forms) (drop 1 forms))]
    `(def ~name ~docstr (quiescent/component (fn ~argvec ~@body)))))


(defmacro wrapper
  [child & body]
  `(-> (quiescent/make-wrapper ~child)
       ~@body
       quiescent/wrapper-compile))

(defmacro on-update
  [v bindings & body]
  `(quiescent/on-update* ~v (fn ~bindings ~@body)))

(defmacro on-mount
  [v bindings & body]
  `(quiescent/on-mount* ~v (fn ~bindings ~@body)))

(defmacro on-will-mount
  [v bindings & body]
  `(quiescent/on-will-mount* ~v (fn ~bindings ~@body)))

(defmacro on-will-update
  [v bindings & body]
  `(quiescent/on-will-update* ~v (fn ~bindings ~@body)))

(defmacro on-will-unmount
  [v bindings & body]
  `(quiescent/on-will-unmount* ~v (fn ~bindings ~@body)))
