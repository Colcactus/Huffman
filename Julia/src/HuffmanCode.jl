module HuffmanCode

using BufferedStreams, FileIO

export ZIP

const DICT = 8

function zipFile(src::String, dir::String)
    bis = BufferedInputStream(open(src, "r"))
    byteZip = HuffmanZip(bis)
    close(bis)
end

end