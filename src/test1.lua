require("hate")

PhysicsEntity = class("PhysicsEntity", Entity)
function PhysicsEntity:initialize(world, pos, size)
	Entity.initialize(self, world, pos)
	self.Size = size
	self.Accel = Vector2(0, 0)
	self.Solid = true
	self.StepSize = 0.1
	self.Gravity = 0.3
	self.Friction = 0.35
	self.Active = true
end

function PhysicsEntity:InAir()
	self.Pos.Y = self.Pos.Y + self.StepSize
	for i = 0, self.Parent.Entities.Size - 1 do
		ent = self.Parent.Entities:Get(i)
		if self ~= ent then
			if self:Collides(ent) then 
				self.Pos.Y = self.Pos.Y - self.StepSize
				return false
			end
		end
	end
	self.Pos.Y = self.Pos.Y - self.StepSize
	return true
end