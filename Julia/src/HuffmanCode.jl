module HuffmanCode

include("Node.jl")

export ZIP

"""
进行赫夫曼编码压缩的函数
"""
function huffmanZIP(bytes::Array{UInt8,1})
    nodes = getNodes(bytes)
end

"""
将UInt8数组转为Node集合
"""
function getNodes(bytes::Array{UInt8,1})
    nodes = []
    # 存储每一个字节出现了多少次
    counts = Dict{UInt8, Integer}
    # 统计每一个字节出现了多少次
    for b in bytes
        count = get(counts,b,-1)
        if count === nothing
            push!(counts,b=>1)
        end
    end
end

end
