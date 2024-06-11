-- frequency limit param
local username = KEYS[1]
local timeWindow = tonumber(ARGV[1]) -- time window, unit: second

-- user access key name in redis
local accessKey = "short-link:user-flow-risk-control:" .. username

-- Atomically increment the access count and retrieve the incremented value
local currentAccessCount = redis.call("INCR", accessKey)

-- set key expiration
redis.call("EXPIRE", accessKey, timeWindow)

-- return current access count
return currentAccessCount
