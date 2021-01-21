"""
Java的手法,Julia的实现
"""
mutable struct Node
    left::Node
    right::Node
    weight::Int
    # byte
    data::UInt8

    function Node(data::UInt8, weight::Int)
        Node.data = data
        Node.weight = weight
    end

    function toString()
        return "Node{'
            weight = $weight
            , data = $data
            '}"
    end
end