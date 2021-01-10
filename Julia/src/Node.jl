struct Node
    data::UInt8
    weight::Int
    left::Node
    right::Node
end

function Node(data::UInt8, weight::Int)
    Node.data=data
    Node.weight=weight
end
